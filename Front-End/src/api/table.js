import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/RiverSearch/list',
    method: 'get',
    params
  })
}
