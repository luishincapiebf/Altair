import "../styles/globals.css";
import {useEffect} from "react";
import {AuthProvider} from "../context/AuthContext";

function MyApp({Component, pageProps}) {
    useEffect(() => {
        const use = async () => {
            (await import("tw-elements")).default;
        };
        use();
    }, []);

    return (<>
            <AuthProvider>
                <Component {...pageProps} />
            </AuthProvider>
        </>);
}

export default MyApp;
