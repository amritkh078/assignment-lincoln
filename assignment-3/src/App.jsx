import PostForm from './components/PostForm';

export default function App() {
  return (
    <div className="min-vh-100 bg-light py-5">
      <div className="container" style={{ maxWidth: '640px' }}>
        <div className="text-center mb-4">
          <h1 className="h3 fw-bold">Create a Post</h1>
          <p className="text-muted">
            Fill in the form below. On submit, data is sent via HTTPS to{' '}
            <a
              href="https://jsonplaceholder.typicode.com/posts"
              target="_blank"
              rel="noreferrer"
              className="text-decoration-none"
            >
              JSONPlaceholder
            </a>{' '}
            (a public REST dummy API).
          </p>
        </div>

        <div className="card shadow-sm">
          <div className="card-body p-4">
            <PostForm />
          </div>
        </div>

        <p className="text-center text-muted mt-4" style={{ fontSize: '0.8rem' }}>
          API base URL: <code>{import.meta.env.VITE_API_BASE_URL}</code>
        </p>
      </div>
    </div>
  );
}
