export type Drink = {
  id: string
  name: string
  ingredients: Ingredient[]
  alcoholContent: number
}

export type Ingredient = {
  name: string
  alcoholPercentage: number | null
  amount: number | null
  unit: string
  type: string
}

export enum unit {
  dl = 'dl',
  cl = 'cl',
  ml = 'ml',
}

export enum type {
  alcohol = 'alcohol',
  mixer = 'mixer',
  extras = 'extras',
}
