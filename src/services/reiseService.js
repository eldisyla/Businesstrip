const BASE_URL = "http://localhost:8080/api/reisen";

export async function getReisen() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function createReise(reise) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(reise),
  });
  return res.json();
}

export async function deleteReise(id) {
  await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
}
