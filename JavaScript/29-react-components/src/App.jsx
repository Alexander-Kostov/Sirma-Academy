import './App.css'
import AdvancedCounter from './components/AdvancedCounter'
import Button from './components/Button'
import Counter from './components/Counter'
import MovieList from './components/MovieList'

const movies = [
  'The Matrix',
  'Lord of the Rings',
  'Harry Potter',
  'Man of Steel'
]

function App() {

  return (
    <>
      <MovieList title="Movies" movies = {movies}/>

      <Button title="Create">Send</Button>
      
      <Counter/>

      <AdvancedCounter/>
    </>
  )
}

export default App

function double(number) {
  return number * 2;
}
