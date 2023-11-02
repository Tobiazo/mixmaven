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
  if (data.status !== 201) {
    console.log(data.status, 'Error: Drink not created')
  }
  return data.json()
}

export const editDrink = async (drink: Drink): Promise<Drink> => {
  const data = await fetch(`${baseURL}/drinks/${drink.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(drink),
  })
  if (data.status !== 200 && data.status !== 204) {
    console.log(data.status, 'Error: Drink not updated')
  }
  return data.json()
}

export const deleteDrink = async (id: string): Promise<void> => {
  await fetch(`${baseURL}/drinks/${id}`, {
    method: 'DELETE',
  })
    .then((res) => console.log(res.status, ' Drink deleted'))
    .catch((err) => console.log(err, ' Error: Drink not deleted'))
}
