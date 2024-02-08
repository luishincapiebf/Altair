export async function shortenUrl(url) {
  try {
    const response = await fetch(`http://localhost:8080/?longUrl=${encodeURIComponent(url)}`, {
      method: 'POST'

    });
    if (response.ok) {
      const responseData = await response.json();
      return responseData.shortUrl; 
    } else {
      throw new Error('Error:', response.statusText);
    }
  } catch (error) {
    throw new Error('Error:', error);
  }
}