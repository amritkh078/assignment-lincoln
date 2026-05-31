import { usePostForm } from '../hooks/usePostForm';

export default function PostForm() {
  const {
    fields, errors, touched, status, apiError, result,
    handleChange, handleBlur, handleSubmit, handleReset,
  } = usePostForm();

  const isLoading = status === 'loading';

  function fieldClass(name) {
    if (!touched[name]) return 'form-control';
    return `form-control ${errors[name] ? 'is-invalid' : 'is-valid'}`;
  }

  if (status === 'success' && result) {
    return (
      <div className="card shadow-sm border-success">
        <div className="card-body">
          <div className="d-flex align-items-center mb-3">
            <span className="text-success fs-3 me-2">&#10003;</span>
            <h4 className="card-title mb-0 text-success">Post Created Successfully!</h4>
          </div>
          <p className="text-muted mb-3">
            JSONPlaceholder accepted your post and returned the following record:
          </p>
          <dl className="row mb-4">
            <dt className="col-sm-3">Post ID</dt>
            <dd className="col-sm-9"><span className="badge bg-secondary fs-6">{result.id}</span></dd>
            <dt className="col-sm-3">User ID</dt>
            <dd className="col-sm-9">{result.userId}</dd>
            <dt className="col-sm-3">Title</dt>
            <dd className="col-sm-9">{result.title}</dd>
            <dt className="col-sm-3">Body</dt>
            <dd className="col-sm-9">{result.body}</dd>
          </dl>
          <button className="btn btn-primary" onClick={handleReset}>
            Create Another Post
          </button>
        </div>
      </div>
    );
  }

  return (
    <form onSubmit={handleSubmit} noValidate>
      {status === 'error' && (
        <div className="alert alert-danger d-flex align-items-start gap-2" role="alert">
          <span className="fs-5">&#9888;</span>
          <div>
            <strong>Submission Failed</strong>
            <p className="mb-0 mt-1">{apiError}</p>
          </div>
        </div>
      )}

      <div className="mb-3">
        <label htmlFor="title" className="form-label fw-semibold">
          Title <span className="text-danger">*</span>
        </label>
        <input
          id="title"
          name="title"
          type="text"
          placeholder="Enter a post title (min. 5 characters)"
          className={fieldClass('title')}
          value={fields.title}
          onChange={handleChange}
          onBlur={handleBlur}
          disabled={isLoading}
        />
        {touched.title && errors.title && (
          <div className="invalid-feedback">{errors.title}</div>
        )}
        {touched.title && !errors.title && (
          <div className="valid-feedback">Looks good!</div>
        )}
      </div>

      <div className="mb-3">
        <label htmlFor="body" className="form-label fw-semibold">
          Body <span className="text-danger">*</span>
        </label>
        <textarea
          id="body"
          name="body"
          rows={4}
          placeholder="Write the post body (min. 10 characters)"
          className={fieldClass('body')}
          value={fields.body}
          onChange={handleChange}
          onBlur={handleBlur}
          disabled={isLoading}
        />
        {touched.body && errors.body && (
          <div className="invalid-feedback">{errors.body}</div>
        )}
        {touched.body && !errors.body && (
          <div className="valid-feedback">Looks good!</div>
        )}
      </div>

      <div className="mb-4">
        <label htmlFor="userId" className="form-label fw-semibold">
          User ID <span className="text-danger">*</span>
        </label>
        <input
          id="userId"
          name="userId"
          type="number"
          min={1}
          max={10}
          placeholder="Enter a user ID (1–10)"
          className={fieldClass('userId')}
          value={fields.userId}
          onChange={handleChange}
          onBlur={handleBlur}
          disabled={isLoading}
        />
        <div className="form-text">Represents the author. Must be an integer between 1 and 10.</div>
        {touched.userId && errors.userId && (
          <div className="invalid-feedback">{errors.userId}</div>
        )}
        {touched.userId && !errors.userId && (
          <div className="valid-feedback">Looks good!</div>
        )}
      </div>

      <div className="d-flex gap-2">
        <button
          type="submit"
          className="btn btn-primary px-4"
          disabled={isLoading}
        >
          {isLoading ? (
            <>
              <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true" />
              Submitting…
            </>
          ) : 'Submit Post'}
        </button>
        <button
          type="button"
          className="btn btn-outline-secondary"
          onClick={handleReset}
          disabled={isLoading}
        >
          Reset
        </button>
      </div>
    </form>
  );
}
