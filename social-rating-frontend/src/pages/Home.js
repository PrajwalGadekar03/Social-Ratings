import { useEffect, useState } from "react";
import API from "../services/api";

function Home() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchTopUsers();
  }, []);

  const fetchTopUsers = async () => {
    const res = await API.get("/ratings/leaderboard");
    setUsers(res.data);
  };

  return (
    <div>
      <h2>Top Rated Users</h2>

      {users.map((u, i) => (
        <div key={i}>
          User ID: {u[0]} | Rating: {u[1]}
        </div>
      ))}
    </div>
  );
}

export default Home;