import request from '@/utils/request'

// 创建畜牧离场
export function createLefter(data) {
  return request({
    url: '/cumu/lefter/create',
    method: 'post',
    data: data
  })
}

// 更新畜牧离场
export function updateLefter(data) {
  return request({
    url: '/cumu/lefter/update',
    method: 'put',
    data: data
  })
}

// 删除畜牧离场
export function deleteLefter(id) {
  return request({
    url: '/cumu/lefter/delete?id=' + id,
    method: 'delete'
  })
}

// 获得畜牧离场
export function getLefter(id) {
  return request({
    url: '/cumu/lefter/get?id=' + id,
    method: 'get'
  })
}
// 获得畜牧离场
export function getLefterById(id) {
  return request({
    url: '/cumu/lefter/getLefterById?id=' + id,
    method: 'get'
  })
}
export function getJoinerByVo(vo) {
  return request({
    url: '/cumu/lefter/getJoinerByVo' ,
    method: 'get',
    params: vo
  })
}

// 获得畜牧离场分页
export function getLefterPage(query) {
  return request({
    url: '/cumu/lefter/page',
    method: 'get',
    params: query
  })
}

// 导出畜牧离场 Excel
export function exportLefterExcel(query) {
  return request({
    url: '/cumu/lefter/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
