import DrinkCard from '../components/DrinkCard'
import '../styles/Home.css'
import { useEffect, useState } from 'react'
import { Drink } from '../types'
import { useAutoAnimate } from '@formkit/auto-animate/react'

const Home = () => {
  const [drinks, setDrinks] = useState<Drink[]>([])
  const [content, setContent] = useState(data);
  const [boxRef] = useAutoAnimate<HTMLDivElement>();

  const fetchData = async () => {
    const data = fetch("http://localhost:8000/drinks").then(res => res.json()).catch(err => console.log(err))
    setDrinks(await data)
  }

  useEffect(() => {
    fetchData()
  }, [])

  const handleDelete = (index: number) => {
    const copy = [...content]
    copy.splice(index, 1)
    setContent(copy)
  }

  return (
    <>
      <div className="title-box">
        <h2>Drinks</h2>
        <p>Here is all the drinks from the community</p>
      </div>

      <div className="drink-box" ref={boxRef}>
        {drinks.length === 0 ? (<p>Loading...</p>) : 
          drinks.map((drink, index) => (
            <DrinkCard content={drink} key={'drink_' + index} handleDelete={() => handleDelete(index)} />
          ))
        }
      </div>
    </>
  )
}

export default Home
