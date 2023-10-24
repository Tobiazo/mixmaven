export type Drink = {
  id: string 
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
  cl = 'cl',
  ml = 'ml',
}

export enum type {
  alcohol = 'alchol',
  mixer = 'mixer',
  extras = 'extras',
}
