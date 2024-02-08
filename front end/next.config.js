/** @type {import('next').NextConfig} */
const nextConfig = {
  trailingSlash: true,
  reactStrictMode: true,
  images: {
    domains: ["flowbite.com"],
    formats: ["image/avif", "image/webp"],
  },
};

module.exports = nextConfig;
