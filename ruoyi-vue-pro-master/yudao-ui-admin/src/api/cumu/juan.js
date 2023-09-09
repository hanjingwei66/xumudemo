import request from '@/utils/request'

// 创建圈
export function createJuan(data) {
  return request({
    url: '/cumu/juan/create',
    method: 'post',
    data: data
  })
}

// 更新圈
export function updateJuan(data) {
  return request({
    url: '/cumu/juan/update',
    method: 'put',
    data: data
  })
}

// 删除圈
export function deleteJuan(id) {
  return request({
    url: '/cumu/juan/delete?id=' + id,
    method: 'delete'
  })
}

// 获得圈
export function getJuan(id) {
  return request({
    url: '/cumu/juan/get?id=' + id,
    method: 'get'
  })
}
// 获得圈
export function getByRegionId(id) {
  return request({
    url: '/cumu/juan/getByRegionId?id=' + id,
    method: 'get'
  })
}

// 获得圈分页
export function getJuanPage(query) {
  return request({
    url: '/cumu/juan/page',
    method: 'get',
    params: query
  })
}

// 导出圈 Excel
export function exportJuanExcel(query) {
  return request({
    url: '/cumu/juan/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
