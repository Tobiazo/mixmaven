import { Drink } from '../types'

const baseURL = 'http://localhost:8000'

export const getDrinks = async (): Promise<Drink[]> => {
  return fetch(`${baseURL}/drinks`, {
    method: 'GET',
  })
    .then((res) => res.json())
    .catch((e) => console.log(e))
}

export const createDrink = async (drink: Drink): Promise<Drink> => {
  const data = await fetch(`${baseURL}/drinks`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(drink),
  })
  // .then((res) => res.json())
  // .then((res) => console.log(res, "Drink Created"))
  // .catch((err) => console.log(err))
  if (data.status !== 201) {
    console.log(data.status, 'Error: Drink not created')
  }
  return data.json()
  // return data.json();
}

export const deleteDrink = async (id: string): Promise<void> => {
  return fetch(`${baseURL}/drinks/${id}`, {
    method: 'DELETE',
  })
    .then((res) => res.json())
    .then((res) => console.log(res, 'Drink Deleted'))
    .catch((err) => console.log(err))
}
