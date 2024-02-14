import Link from 'next/link';
import Image from 'next/image';
import {useUrl} from "../../hooks/useUrl";

export default function ShortUrlCard({urlVal}) {
    const {data, error, isValidating} = useUrl(urlVal);

    //TODO: Add tooltip to the button
    const handleCopyUrl = () => {
        navigator.clipboard.writeText(data.shortUrl).then(r => console.log("copied"));
    }
    if (isValidating)
        return <>
            <div role="status" className="max-w-sm animate-pulse">
                <div className="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 w-48 mb-4"></div>
                <div className="h-2 bg-gray-200 rounded-full dark:bg-gray-700 max-w-[360px] mb-2.5"></div>
                <div className="h-2 bg-gray-200 rounded-full dark:bg-gray-700 max-w-[360px]"></div>
                <div
                    className="flex items-center justify-center w-full h-48 bg-gray-300 rounded sm:w-96 dark:bg-gray-700">
                    <svg className="w-10 h-10 text-gray-200 dark:text-gray-600" aria-hidden="true"
                         xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 18">
                        <path
                            d="M18 0H2a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2Zm-5.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3Zm4.376 10.481A1 1 0 0 1 16 15H4a1 1 0 0 1-.895-1.447l3.5-7A1 1 0 0 1 7.468 6a.965.965 0 0 1 .9.5l2.775 4.757 1.546-1.887a1 1 0 0 1 1.618.1l2.541 4a1 1 0 0 1 .028 1.011Z"/>
                    </svg>
                </div>
                <span className="sr-only">Loading...</span>
            </div>
        </>

    if (error) return <div>Error fetching data</div>
    return (

        <div>
            <h2 className="text-1xl font-semibold text-gray-800 my-2">Shortened URL</h2>

            <div className="flex items-center gap-1">
                <h3 className="text-gray-500 text-sm">Created:</h3>
                <p className="text-gray-500 text-sm">{data.createdAt}</p>
            </div>
            <div className="flex items-center gap-1">
                <h3 className="text-gray-500 text-sm">Expires:</h3>
                <p className="text-gray-500 text-sm">{data.expiresAt}</p>
            </div>
            <div className="flex items-center gap-1">
                <h3 className="text-gray-500 text-sm">Clicks:</h3>
                <p className="text-gray-500 text-sm">{data.accessCount}</p>
            </div>
            <div className="flex gap-4 items-baseline">
                <Link href={data.shortUrl} passHref={true}>
                    <p className="text-blue-800">{data.shortUrl}</p>
                </Link>
                <button onClick={handleCopyUrl}
                        className="bg-blue-800  hover:bg-blue-700 font-bold  text-white px-2 py-1 rounded-md">
                    Copy URL
                </button>
            </div>
            <div className="mt-4">
                <Image src={"data:image/png;base64, " + data.qrCode} alt="QR Code" width={200} height={200}/>
            </div>
        </div>
    );
}