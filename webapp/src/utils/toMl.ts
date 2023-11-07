import { unit } from '../types'

const toMl = (amount: number, u: string) => {
  switch (u) {
    case unit.cl:
      return amount * 10
    case unit.dl:
      return amount * 100
    case unit.g:
      return 0
    default:
      return amount
  }
}

export default toMl
