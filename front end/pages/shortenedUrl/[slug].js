import React from "react";
import Layout from "../../components/Layout";
import ShortenedUrlCard from "../../components/shortenedUrl/ShortUrlCard";

export default function ShortenedUrl({qrCodeUrl}) {

    return (
        <Layout>
            <ShortenedUrlCard qrCodeUrl={qrCodeUrl}/>
        </Layout>
    );
}