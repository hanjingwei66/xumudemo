import request from '@/utils/request'

// 创建畜牧进场
export function createJoiner(data) {
  return request({
    url: '/cumu/joiner/create',
    method: 'post',
    data: data
  })
}

// 更新畜牧进场
export function updateJoiner(data) {
  return request({
    url: '/cumu/joiner/update',
    method: 'put',
    data: data
  })
}

// 删除畜牧进场
export function deleteJoiner(id) {
  return request({
    url: '/cumu/joiner/delete?id=' + id,
    method: 'delete'
  })
}

// 获得畜牧进场
export function getJoiner(id) {
  return request({
    url: '/cumu/joiner/get?id=' + id,
    method: 'get'
  })
}// 获得畜牧进场
export function getJoinerById(id) {
  return request({
    url: '/cumu/joiner/getJoinerById?id=' + id,
    method: 'get'
  })
}

// 获得畜牧进场分页
export function getJoinerPage(query) {
  return request({
    url: '/cumu/joiner/page',
    method: 'get',
    params: query
  })
}

// 导出畜牧进场 Excel
export function exportJoinerExcel(query) {
  return request({
    url: '/cumu/joiner/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
