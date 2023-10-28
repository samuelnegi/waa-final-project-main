import axios from 'axios'
const baseURL = 'http://localhost:8080'

export const login = async (email: string, password: string) => {
    const response = await axios.post(`${baseURL}/api/auth/login`, {
        email, password
    })
    return response.data.accessToken
}

export const getProducts = async (token: string, page: number) => {
    const q = `?page=${page || 0}`
    const response = await axios.get(`${baseURL}/api/products${q}`, { headers: { Authorization: `Bearer ${token}` } })
    return response.data.content
}