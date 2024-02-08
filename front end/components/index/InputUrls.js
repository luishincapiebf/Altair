import React, { useState } from "react";
import { useRouter } from 'next/router';
import { shortenUrl } from "../../pages/api/url";

export default function InputUrls() {
  const [urlVal, setUrlVal] = useState("");
  const router = useRouter();

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await fetch('/api/proxy?longUrl=' + encodeURIComponent(urlVal), {
        method: 'POST'
     
      });
      if (response.ok) {
        const responseData = await response.json();
        router.push(`/shortenedUrl/${encodeURIComponent(responseData.shortUrl)}`);
      } else {
        console.error('Error:', response.statusText);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }

  function onChangeTagInput(e) {
    setUrlVal(e.target.value);
  }
  return (
    <>
   <section className="max-w-6xl mx-auto px-2 sm:px-6 mt-5 sm:pt-3">
        <div className="mx-auto sm:max-w-xl md:max-w-full lg:max-w-screen-xl md:px-24 lg:px-8">
          <h2 className="text-2xl font-bold text-gray-800 my-2">URL shortener</h2>
          <div className="flex items-center">
          <form onSubmit={(e) => handleSubmit(e)}>
              <input
                type="url"
                className="w-72 md:w-80  xl:w-96 border border-gray-300 rounded-lg px-3 py-2 mr-2"
                title="Números separados por coma"
                onChange={(e) => onChangeTagInput(e)}
                placeholder="https://example.com"
                pattern="[Hh][Tt][Tt][Pp][Ss][www]?:\/\/(?:(?:[a-zA-Z\u00a1-\uffff0-9]+-?)*[a-zA-Z\u00a1-\uffff0-9]+)(?:\.(?:[a-zA-Z\u00a1-\uffff0-9]+-?)*[a-zA-Z\u00a1-\uffff0-9]+)*(?:\.(?:[a-zA-Z\u00a1-\uffff]{2,}))(?::\d{2,5})?(?:\/[^\s]*)?"
              />
              <button className="bg-blue-800  hover:bg-blue-700 font-bold  text-white px-3 py-2 rounded-lg">
                Generate
              </button>
            </form>
          </div>
          <span className="text-gray-500 text-sm">
            Enter a valid URL
          </span>
        </div>
      </section>
    </>
  );
}
