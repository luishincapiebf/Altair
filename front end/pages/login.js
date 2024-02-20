import React, {useState} from 'react'
import {useRouter} from 'next/router'
import {useAuth} from '../context/AuthContext'
import {altairApi} from './api/AltairApi'
import {parseJwt, handleLogError} from '../misc/Helpers'
import Layout from "../components/Layout";

function Login() {
    const Auth = useAuth()
    const isLoggedIn = Auth.userIsAuthenticated()
    const router = useRouter()

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [isError, setIsError] = useState(false)

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        if (name === 'username') {
            setUsername(value)
        } else if (name === 'password') {
            setPassword(value)
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        if (!(username && password)) {
            setIsError(true)
            return
        }

        try {
            const response = await altairApi.authenticate(username, password)
            const data = parseJwt(response.accessToken)
            const authenticatedUser = {data, accessToken}
            Auth.userLogin(authenticatedUser)

            setUsername('')
            setPassword('')
            setIsError(false)
            router.push('/')
        } catch (error) {
            handleLogError(error)
            setIsError(true)
        }
    }

    if (isLoggedIn) {
        router.push('/')
    }

    return (
        <Layout>
            <div className="flex justify-center">
                <div className="max-w-md w-full">
                    <form className="bg-white rounded px-8 pt-6 pb-8 mb-4" onSubmit={handleSubmit}>
                        <div className="mb-4">
                            <input
                                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none "
                                autoFocus
                                name='username'
                                placeholder='Username'
                                value={username}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="mb-6">
                            <input
                                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none "
                                name='password'
                                placeholder='Password'
                                type='password'
                                value={password}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="flex items-center justify-between">
                            <button
                                className="bg-blue-800 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none  w-full"
                                type="submit">
                                Login
                            </button>
                        </div>
                    </form>
                    <p className="text-center text-gray-500 text-xs">
                        {"Don't have already an account?"} <a href="/signup"
                                                              className="text-blue-500 hover:blue">Sign
                        Up</a>
                    </p>
                    {isError &&
                        <p className="text-red-500 text-xs italic">The username or password provided are incorrect!</p>}
                </div>
            </div>
        </Layout>
    )
}

export default Login