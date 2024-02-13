import useSWR from "swr";

const fetcher = (...args) => fetch(...args).then(res => res.json())

export function useUrl(urlVal) {

    console.log("urlVal", urlVal)
    const {data, error, isValidating} = useSWR(`/api/proxy?longUrl=${encodeURIComponent(urlVal)}`, fetcher);

    return {
        data: data,
        isValidating,
        isError: error
    }
}