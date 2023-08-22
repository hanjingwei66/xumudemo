import http from './public'
const baseUrl = '/api/app-api'
export const getPage = (params) => {
  return http.fetchGet(`${baseUrl}/sailing/master/page`, params)
}
export const getInfo = (params) => {
  return http.fetchGet(`${baseUrl}/sailing/sailing-info/get`, params)
}
export const getTemplateListByInfoId = (params) => {
  return http.fetchGet(`${baseUrl}/sailing/template/getTemplateListByInfoId`, params)
}

