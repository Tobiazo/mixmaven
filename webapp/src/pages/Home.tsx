import DrinkCard from '../components/DrinkCard'
import '../styles/Home.css'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { getDrinks } from '../api/drinks'
import { useQuery } from '@tanstack/react-query'
import StatusMessage from '../components/StatusMessage'

const Home = () => {
  const [boxRef] = useAutoAnimate<HTMLDivElement>()

  const getDrinksQuery = useQuery({
    queryKey: ['drinks'],
    queryFn: getDrinks,
  })

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
              id={drink.id}
              key={'drink_' + drink.id}
            />
          ))
        )}
      </div>
    </>
  )
}

export default Home
