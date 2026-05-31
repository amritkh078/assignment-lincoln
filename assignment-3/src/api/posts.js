import client from './client';

export async function createPost(data) {
  const response = await client.post('/posts', data);
  return response.data;
}
