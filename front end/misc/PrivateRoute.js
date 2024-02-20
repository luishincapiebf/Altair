import React from 'react'
import { useRouter } from 'next/router'
import { useAuth } from '../context/AuthContext'

function PrivateRoute({ children }) {
  const { userIsAuthenticated } = useAuth()
  const router = useRouter()

  if (!userIsAuthenticated()) {
    router.push('/login')
    return null
  }

  return children
}

export default PrivateRoute