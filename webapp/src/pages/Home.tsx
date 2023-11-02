import DrinkCard from '../components/DrinkCard'
import '../styles/Home.css'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { UseQueryResult } from '@tanstack/react-query'
import StatusMessage from '../components/StatusMessage'
import { Drink } from '../types'

const Home = ({ query }: { query: UseQueryResult<Drink[], Error>}) => {
  const [boxRef] = useAutoAnimate<HTMLDivElement>()

  // .sort((a, b) => {
  //   const nameA = a.name.toUpperCase();
  //   const nameB = b.name.toUpperCase();
  //   return (nameA < nameB) ? -1 : (nameA > nameB) ? 1 : 0;
  // })
  return (
    <>
      <div className="title-box">
        <h2>Drinks</h2>
        <div className="color-description">
          <p className="alcohol">Alcoholic drink</p>
          <p>Non-alcoholic</p>
        </div>
        {/* <p>Here is all the drinks from the community</p> */}
      </div>
      <div className="drink-box" ref={boxRef}>
        {query.isPending ||
        query.isLoading ||
        query.isError ? (
          <StatusMessage query={query} />
        ) : (
          query.data.map((drink) => (
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
