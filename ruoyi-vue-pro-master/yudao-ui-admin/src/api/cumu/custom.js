import request from '@/utils/request'

// 创建畜牧用户
export function createCustom(data) {
  return request({
    url: '/cumu/custom/create',
    method: 'post',
    data: data
  })
}

// 更新畜牧用户
export function updateCustom(data) {
  return request({
    url: '/cumu/custom/update',
    method: 'put',
    data: data
  })
}

// 删除畜牧用户
export function deleteCustom(id) {
  return request({
    url: '/cumu/custom/delete?id=' + id,
    method: 'delete'
  })
}

// 获得畜牧用户
export function getCustom(id) {
  return request({
    url: '/cumu/custom/get?id=' + id,
    method: 'get'
  })
}

// 获得畜牧用户分页
export function getCustomPage(query) {
  return request({
    url: '/cumu/custom/page',
    method: 'get',
    params: query
  })
}

// 导出畜牧用户 Excel
export function exportCustomExcel(query) {
  return request({
    url: '/cumu/custom/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
