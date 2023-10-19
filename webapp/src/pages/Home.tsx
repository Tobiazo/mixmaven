import DrinkCard from '../components/DrinkCard'
import data from '../../fixtures/data.json'
import '../styles/Home.css'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { useState } from 'react'

const Home = () => {
  const [content, setContent] = useState(data);
  const [boxRef] = useAutoAnimate<HTMLDivElement>();

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
        {content.map((drink, index) => (
          <DrinkCard content={drink} key={'drink_' + index} handleDelete={() => handleDelete(index)} />
        ))}
      </div>
    </>
  )
}

export default Home
