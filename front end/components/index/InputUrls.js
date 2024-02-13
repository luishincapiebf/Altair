import {useState} from "react";
import ShortUrlCard from "../shortenedUrl/ShortUrlCard";


export default function InputUrls() {
    const [urlVal, setUrlVal] = useState("");
    const [isSubmitted, setSubmitted] = useState(false);

    function handleChange(e) {
        setUrlVal(e.target.value);
        setSubmitted(false);
    }

    function handleSubmit(e) {
        e.preventDefault();
        setSubmitted(true);
    }

    return (
        <>
            <section className="flex-col max-w-6xl mx-auto px-2 sm:px-6 mt-5 sm:pt-3">
                <h2 className="text-2xl font-bold text-gray-800 my-2">URL shortener</h2>
                <div className="flex items-center">
                    <form className="flex flex-row space-y-3" onSubmit={(e) => handleSubmit(e)}>
                        <label htmlFor="urlVal" className="flex flex-col text-gray-500 text-sm">
                            <input
                                name={"urlVal"}
                                type="url"
                                className="w-72 md:w-96  xl:w-96 border border-gray-300 rounded-lg px-3 py-2 mr-2"
                                title="Enter a valid URL"
                                value={urlVal}
                                onChange={(e) => handleChange(e)}
                                placeholder="https://example.com"
                                pattern="[Hh][Tt][Tt][Pp][Ss][www]?:\/\/(?:(?:[a-zA-Z\u00a1-\uffff0-9]+-?)*[a-zA-Z\u00a1-\uffff0-9]+)(?:\.(?:[a-zA-Z\u00a1-\uffff0-9]+-?)*[a-zA-Z\u00a1-\uffff0-9]+)*(?:\.(?:[a-zA-Z\u00a1-\uffff]{2,}))(?::\d{2,5})?(?:\/[^\s]*)?"
                            />
                            Enter a valid URL
                        </label>
                        <button
                            type="submit"
                            className="bg-blue-800 hover:bg-blue-700 font-bold text-white px-3 py-2 rounded-lg">
                            Generate
                        </button>
                    </form>
                </div>
                {isSubmitted && <ShortUrlCard urlVal={urlVal}/>}
            </section>
        </>
    );
}
