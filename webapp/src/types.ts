export type Drink = {
  id: number
  name: string
  ingredients: Ingredient[]
  alcoholContent: number
}

export type Ingredient = {
  name: string
  alcoholPercentage?: number
  amount: number
  unit: string
  type: string
}

export enum unit {
  dl = 'dl',
  ml = 'ml',
}

export enum type {
  alcohol = 'alchol',
  mixer = 'mixer',
  extras = 'extras',
}
