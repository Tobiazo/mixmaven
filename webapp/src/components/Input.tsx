import { useState } from 'react'

type Props = {
  type?: 'text' | 'number' | 'alcohol'
  label: string
  value: string | number
  onChange: (e: string) => void
}

const Input = ({ type = 'text', label, value, onChange }: Props) => {
  const [isError, setError] = useState(false)
  const [errorMsg, setErrorMsg] = useState('Invalid Field')

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.value === '' && type !== 'alcohol') {
      setError(true)
      setErrorMsg('This field is required')
    } else {
      setError(false)
    }

    // Only numbers, max one dot and max 1 decimal
    const regex = /^\d*\.?\d{0,1}$/

    if (
      (type === 'number' || type === 'alcohol') &&
      !regex.test(e.target.value)
    ) {
      setError(true)
      setErrorMsg('This field must be a postive number and max one decimal')
      setTimeout(() => {
        setError(false)
      }, 1000)
      return
    }

    if (type === 'alcohol' && parseInt(e.target.value) > 100) {
      setError(true)
      setErrorMsg('This field cannot be greater than 100')
      setTimeout(() => {
        setError(false)
      }, 1000)
      return
    }

    onChange(e.target.value)
  }

  return (
    <div className="form-input">
      <input
        id={label.toLowerCase().replace(' ', '-')}
        type="text"
        value={value}
        required
        onChange={handleChange}
      />
      <span>{label}</span>
      <p
        className={`invalidMsg ${isError && 'visible'}`}
        id={label.toLowerCase().replace(' ', '-') + '-error'}
      >
        {errorMsg}
      </p>
    </div>
  )
}

export default Input
