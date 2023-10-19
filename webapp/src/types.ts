export type Drink = {
  id: number;
  name: string;
  ingredients: Ingredient[];
  alcoholContent: string;
};

export type Ingredient = {
  name: string;
  alcoholPercentage?: number;
  amount: number;
  unit: unit;
  type: type;
};

export enum unit {
  dl = "dl",
  ml = "ml",
}

export enum type {
  alcohol = "alchol",
  mixer = "mixer",
  extras = "extras",
}
