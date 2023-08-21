import http from './public'
const baseUrl = '/api/app-api'
// 获取vx登录二维码
export const getQrCode = (params) => {
  return http.fetchGet(`${baseUrl}/member/wx-mp/getQrCode`, params)
}
export const getQrCodeTest = (params) => {
  return http.fetchGet(`${baseUrl}/member/wx-mp/checkSign1`, params)
}
export const getTicket = (params) => {
  return http.fetchGet(`${baseUrl}/member/wx-mp/getTicket`, params)
}
