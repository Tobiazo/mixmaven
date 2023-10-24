import DrinkCard from '../components/DrinkCard'
import '../styles/Home.css'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { deleteDrink, getDrinks } from '../api/drinks'
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

const Home = () => {
  const [boxRef] = useAutoAnimate<HTMLDivElement>();

  const queryClient = useQueryClient();

  const { data, isLoading, isPending, isError } = useQuery({
    queryKey: ["drinks"],
    queryFn: getDrinks
  })

  const deleteMutation = useMutation({
    mutationFn: deleteDrink,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["drinks"], exact: true })
    }
  })
  
  if (isPending || isLoading)
    return <p>Loading..</p>

  if (isError)
    return <p>ERROR!</p>

  

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
        {data.map((drink) => (
            <DrinkCard content={drink} key={'drink_' + drink.id} handleDelete={() => handleDelete(drink.id)} />
          ))
        }
      </div>
    </>
  )
}

export default Home
