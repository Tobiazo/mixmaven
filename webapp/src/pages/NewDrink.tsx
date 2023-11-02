import { useMutation, useQueryClient } from '@tanstack/react-query'
import { createDrink } from '../api/drinks'
import { Drink } from '../types'
import '../styles/DrinkForm.css'
import { useNavigate } from 'react-router-dom'
import DrinkForm from '../components/DrinkForm'

const NewDrink = () => {
  const INIT_VALUES = {
    name: '',
    ingredients: [],
  }

  const queryClient = useQueryClient()
  const navigate = useNavigate()

  const createDrinkMutation = useMutation({
    mutationKey: ['createDrink'],
    mutationFn: createDrink,
    onSuccess: (data: Drink) => {
      queryClient.setQueryData(['drinks'], (prev: Drink[]) => [...prev, data])
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
      navigate('/')
    },
    onError: (error) => {
      console.log(error)
    },
  })

  return (
    <>
      <div className="new-drink-container">
        <h2>Create a new drink</h2>
        <DrinkForm submit={createDrinkMutation} INIT_VALUES={INIT_VALUES} />
      </div>
    </>
  )
}

export default NewDrink
