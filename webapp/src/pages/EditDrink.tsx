import {
  UseQueryResult,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query'
import DrinkForm from '../components/DrinkForm'
import { useNavigate, useParams } from 'react-router-dom'
import { Drink } from '../types'
import { editDrink } from '../api/drinks'
import StatusMessage from '../components/StatusMessage'

const EditDrink = ({ query }: { query: UseQueryResult<Drink[], Error> }) => {
  const { id } = useParams()
  const queryClient = useQueryClient()
  const navigate = useNavigate()

  const drink = (
    (queryClient.getQueryData(['drinks']) as Array<Drink>) || []
  ).find((drink) => drink.id === id, null)

  const editMutation = useMutation({
    mutationFn: editDrink,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
      navigate('/')
    },
  })

  return (
    <div className="edit-drink-container">
      <h2>Edit drink</h2>
      {query.isPending || query.isLoading || query.isError ? (
        <StatusMessage query={query} />
      ) : drink === undefined ? (
        <p>Drink is undefined</p>
      ) : (
        <DrinkForm submit={editMutation} INIT_VALUES={drink} id={id} />
      )}
    </div>
  )
}

export default EditDrink
