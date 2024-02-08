import Layout from "../components/Layout";
import InputUrls from "../components/index/InputUrls";

let menuItems = [
  { name: "Login", href: "/login" },
];
export default function Home() {
  return (
    <Layout>
      <InputUrls/>
    </Layout>
  );
}
