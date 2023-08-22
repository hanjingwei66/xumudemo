import http from './public'
const baseUrl = '/api/app-api'
export const getQrCode = (params) => {
  return http.fetchPost(`${baseUrl}/pay/createOrder`, params)
}
