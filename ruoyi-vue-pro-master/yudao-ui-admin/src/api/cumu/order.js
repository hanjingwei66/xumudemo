import request from '@/utils/request'

// 创建畜牧订单交易
export function createOrder(data) {
  return request({
    url: '/cumu/order/create',
    method: 'post',
    data: data
  })
}

// 更新畜牧订单交易
export function updateOrder(data) {
  return request({
    url: '/cumu/order/update',
    method: 'put',
    data: data
  })
}

// 删除畜牧订单交易
export function deleteOrder(id) {
  return request({
    url: '/cumu/order/delete?id=' + id,
    method: 'delete'
  })
}

// 获得畜牧订单交易
export function getOrder(id) {
  return request({
    url: '/cumu/order/get?id=' + id,
    method: 'get'
  })
}

// 获得畜牧订单交易分页
export function getOrderPage(query) {
  return request({
    url: '/cumu/order/page',
    method: 'get',
    params: query
  })
}

// 导出畜牧订单交易 Excel
export function exportOrderExcel(query) {
  return request({
    url: '/cumu/order/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
