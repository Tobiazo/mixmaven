import DrinkCard from '../components/DrinkCard'
import '../styles/Home.css'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { deleteDrink, getDrinks } from '../api/drinks'
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import StatusMessage from '../components/StatusMessage'

const Home = () => {
  const [boxRef] = useAutoAnimate<HTMLDivElement>()

  const queryClient = useQueryClient()

  const getDrinksQuery = useQuery({
    queryKey: ['drinks'],
    queryFn: getDrinks,
  })

  const deleteMutation = useMutation({
    mutationFn: deleteDrink,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
    },
  })

  const handleDelete = (id: string) => {
    deleteMutation.mutate(id)
  }

  return (
    <>
      <div className="title-box">
        <h2>Drinks</h2>
        <p>Here is all the drinks from the community</p>
      </div>
      <div className="drink-box" ref={boxRef}>
        {getDrinksQuery.isPending ||
        getDrinksQuery.isLoading ||
        getDrinksQuery.isError ? (
          <StatusMessage query={getDrinksQuery} />
        ) : (
          getDrinksQuery.data.map((drink) => (
            <DrinkCard
              content={drink}
              key={'drink_' + drink.id}
              handleDelete={() => handleDelete(drink.id)}
            />
          ))
        )}
      </div>
    </>
  )
}

export default Home
