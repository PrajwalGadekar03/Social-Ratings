import { useState } from "react";
import API from "../services/api";

function Signup() {
  const [user, setUser] = useState({});

  const handleSignup = async () => {
    try {
      await API.post("/users/signup", user);
      alert("Signup successful");
    } catch {
      alert("Signup failed");
    }
  };

  return (
    <div>
      <h2>Signup</h2>

      <input placeholder="First Name" onChange={(e) => setUser({...user, firstName: e.target.value})} />
      <input placeholder="Email" onChange={(e) => setUser({...user, email: e.target.value})} />
      <input placeholder="Password" onChange={(e) => setUser({...user, password: e.target.value})} />

      <button onClick={handleSignup}>Signup</button>
    </div>
  );
}

export default Signup;