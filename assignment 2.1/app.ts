import { fetchUsers } from "./services/userService";

async function main() {
  const users = await fetchUsers();

  console.log(users);
}

main();