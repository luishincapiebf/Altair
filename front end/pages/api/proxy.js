export default async function handler(req, res) {
    try {
      const response = await fetch(`http://localhost:8080/?longUrl=${encodeURIComponent(req.query.longUrl)}`, {
        method: 'POST'
      });
      if (response.ok) {
        const data = await response.json();
        res.status(200).json(data);
      } else {
        throw new Error('Error:', response.statusText);
      }
    } catch (error) {
      console.error('Error:', error);
      res.status(500).json({ error: 'Error fetching data' });
    }
  }