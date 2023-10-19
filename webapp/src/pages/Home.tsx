import DrinkCard from '../components/DrinkCard'
import data from '../../fixtures/data.json'
import '../styles/Home.css'

const Home = () => {
  return (
    <>
      <div className="title-box">
        <h2>Drinks</h2>
        <p>Here is all the drinks from the community</p>
      </div>

      <div className="drink-box">
        {data.map((drink, index) => (
          <DrinkCard content={drink} key={'drink_' + index} />
        ))}
      </div>
    </>
  )
}

export default Home
