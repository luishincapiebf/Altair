import Link from 'next/link';
import Image from 'next/image';
import {useUrl} from "../../hooks/useUrl";

export default function ShortUrlCard({urlVal}) {
    const {data, error, isValidating} = useUrl(urlVal);

    //TODO: Add tooltip to the button
    const handleCopyUrl = () => {
        navigator.clipboard.writeText(data.shortUrl).then(r => console.log("copied"));
    }
    if (isValidating) return  <div>Loading...</div>
    if (error) return <div>Error fetching data</div>
    return (

        <div>
            <h2 className="text-2xl font-bold text-gray-800 my-2">Shortened URL</h2>
            <div className="flex gap-4">
                <Link href={data.shortUrl} passHref={true}>
                    <p className="text-blue-800 mt-2">{data.shortUrl}</p>
                </Link>
                <button onClick={handleCopyUrl}
                        className="bg-blue-800  hover:bg-blue-700 font-bold  text-white px-2 py-1 rounded-md">
                    Copy URL
                </button>
            </div>
            <div className="mt-4">
                {/*<Image src={"data:image/png;base64, " + myState} alt="QR Code" width={200} height={200}/>*/}
                <Image src={"data:image/png;base64, " +data.qrCode} alt="QR Code" width={200} height={200}/>
            </div>
        </div>
    );
}