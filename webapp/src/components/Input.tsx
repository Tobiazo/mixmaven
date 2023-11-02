import { useState } from 'react'

const Input = ({
  type = 'text',
  label,
  value,
  onChange,
}: {
  type?: 'text' | 'number' | 'alcohol'
  label: string
  value: string | number
  onChange: (e: string) => void
}) => {
  // const [value, setValue] = useState('')
  const [isError, setError] = useState(false)
  const [errorMsg, setErrorMsg] = useState('Invalid Field')

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.value === '' && type !== 'alcohol') {
      setError(true)
      setErrorMsg('This field is required')
    } else {
      setError(false)
    }

    const regex = /^\d*\.?\d*$/ // Only numbers and max one dot
    if (
      (type === 'number' || type === 'alcohol') &&
      !regex.test(e.target.value)
    ) {
      setError(true)
      setErrorMsg('This field must be a postive number and max one dot')
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

    // setValue(e.target.value)
    onChange(e.target.value)
  }

  return (
    <div className="form-input">
      <input
        id={label.toLowerCase().replace(' ', '-')}
        type={type === 'alcohol' ? 'number' : type}
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
