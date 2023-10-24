import { Drink } from "../types";

const baseURL = "http://localhost:8000";

export const getDrinks = async ():Promise<Drink[]> => {
    return fetch(`${baseURL}/drinks`, {
        method: "GET"
    })
    .then(res => res.json())
    .catch(e => console.log(e))
}

export const createDrink = async (drink: Drink):Promise<void> => {
    fetch(`${baseURL}/drinks`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(drink)
    })
    .then(res => res.json())
    .then(res => console.log(res))
    .catch(err => console.log(err));
}


export const deleteDrink = async (id: string):Promise<void> => {
    fetch(`${baseURL}/drinks/${id}`, {
        method: "DELETE"
    })
    .then(res => res.json())
    .then(res => console.log(res,"Drink Deleted"))
    .catch(err => console.log(err))

}


