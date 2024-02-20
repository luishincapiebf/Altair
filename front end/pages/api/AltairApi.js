import {parseJwt} from '../../misc/Helpers'
import {config} from '../../misc/Constants'

export const altairApi = {
    authenticate,
    signup,
    handler,

}

function authenticate(username, password) {
    return fetch(baseURL + '/auth/authenticate', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({username, password})
    }).then(response => response.json())
}

function signup(user) {
    return fetch(baseURL + '/auth/signup', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify(user)
    }).then(response => response.json())
}

function handler(req, res) {
    return fetch(baseURL + `/?longUrl=${encodeURIComponent(req.query.longUrl)}`, {
        method: 'POST'
    }).then(response => response.json())
}

const baseURL = config.url.API_BASE_URL;

// -- Fetch

const instance = (url, options) => {
    if (options.headers && options.headers.Authorization) {
        const token = options.headers.Authorization.split(' ')[1]
        const data = parseJwt(token)
        if (Date.now() > data.exp * 1000) {
            window.location.href = "/login"
        }
    }
    return fetch(url, options).then(response => {
        if (!response.ok) {
            throw new Error(response.statusText)
        }
        return response.json()
    })
}