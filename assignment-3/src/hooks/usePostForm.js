import { useState } from 'react';
import { createPost } from '../api/posts';

const INITIAL = { title: '', body: '', userId: '' };

function validate(fields) {
  const errors = {};
  if (!fields.title.trim()) errors.title = 'Title is required.';
  else if (fields.title.trim().length < 5) errors.title = 'Title must be at least 5 characters.';

  if (!fields.body.trim()) errors.body = 'Body is required.';
  else if (fields.body.trim().length < 10) errors.body = 'Body must be at least 10 characters.';

  const uid = Number(fields.userId);
  if (!fields.userId) errors.userId = 'User ID is required.';
  else if (!Number.isInteger(uid) || uid < 1 || uid > 10)
    errors.userId = 'User ID must be a whole number between 1 and 10.';

  return errors;
}

export function usePostForm() {
  const [fields, setFields] = useState(INITIAL);
  const [errors, setErrors] = useState({});
  const [touched, setTouched] = useState({});
  const [status, setStatus] = useState(null); // null | 'loading' | 'success' | 'error'
  const [apiError, setApiError] = useState('');
  const [result, setResult] = useState(null);

  function handleChange(e) {
    const { name, value } = e.target;
    setFields((f) => ({ ...f, [name]: value }));
    if (touched[name]) {
      const errs = validate({ ...fields, [name]: value });
      setErrors((prev) => ({ ...prev, [name]: errs[name] }));
    }
  }

  function handleBlur(e) {
    const { name } = e.target;
    setTouched((t) => ({ ...t, [name]: true }));
    const errs = validate(fields);
    setErrors((prev) => ({ ...prev, [name]: errs[name] }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setTouched({ title: true, body: true, userId: true });
    const errs = validate(fields);
    setErrors(errs);
    if (Object.keys(errs).length > 0) return;

    setStatus('loading');
    setApiError('');
    setResult(null);

    try {
      const data = await createPost({
        title: fields.title.trim(),
        body: fields.body.trim(),
        userId: Number(fields.userId),
      });
      setResult(data);
      setStatus('success');
      setFields(INITIAL);
      setTouched({});
      setErrors({});
    } catch (err) {
      setStatus('error');
      if (err.response) {
        setApiError(`Server error ${err.response.status}: ${err.response.statusText || 'Unexpected error.'}`);
      } else if (err.request) {
        setApiError('Network error — no response received. Check your connection and try again.');
      } else {
        setApiError(`Request failed: ${err.message}`);
      }
    }
  }

  function handleReset() {
    setFields(INITIAL);
    setErrors({});
    setTouched({});
    setStatus(null);
    setApiError('');
    setResult(null);
  }

  return { fields, errors, touched, status, apiError, result, handleChange, handleBlur, handleSubmit, handleReset };
}
