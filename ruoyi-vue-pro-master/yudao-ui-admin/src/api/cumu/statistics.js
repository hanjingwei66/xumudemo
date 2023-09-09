import request from '@/utils/request'
// 获得畜牧订单交易
export function getStatistics(id) {
  return request({
    url: '/cumu/statistics/getStatistics',
    method: 'get'
  })
}
