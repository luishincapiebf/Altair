import React from "react";
import { useRouter } from 'next/router'
import Layout from "../../components/Layout";
import Link from 'next/link';    

export default function ShortenedUrl() {
  const router = useRouter()

  const handleCopyUrl = () => {
    const url = "http://localhost:8080/" + router.query.slug;
    navigator.clipboard.writeText(url)
  }

  return (
    <Layout>
      <div className="max-w-6xl mx-auto px-2 sm:px-6 mt-5 sm:pt-3">
      <div className="mx-auto sm:max-w-xl md:max-w-full lg:max-w-screen-xl md:px-24 lg:px-8">
        <h2 className="text-2xl font-bold text-gray-800 my-2">Shortened URL</h2>
        {/* //TODO<p className="text-blue-800 mt-2"> {process.env.SERVER_URL +"/"+ router.query.slug}</p> */}
        <div className="flex gap-4">
          <Link href={"http://localhost:8080/"+ router.query.slug} passHref={true}>
        <p  className="text-blue-800 mt-2">{ "http://localhost:8080/"+ router.query.slug}</p>
        </Link>
          <button onClick={handleCopyUrl} className="bg-blue-800  hover:bg-blue-700 font-bold  text-white px-2 py-1 rounded-md">
          Copy URL
          </button>
        </div>
      </div>
    </div>
    </Layout>
  );
}