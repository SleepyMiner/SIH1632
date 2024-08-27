import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import "@ui5/webcomponents-icons/dist/menu2.js";
import { useState } from "react";

interface SetActivePage {
  (actiPg: string): void;
}
interface NavbarProps {
  activePage: string;
  setActivePage: SetActivePage;
}

export default function Navbar({ activePage, setActivePage }: NavbarProps) {
  const [nav, setNav] = useState(false);

  const handleNav = () => {
    setNav(!nav);
  };

  const navItems = [
    { id: 1, text: "Home", href: "/" },
    { id: 2, text: "Jobs", href: "/jobs" },
    { id: 3, text: "Internship", href: "#" },
    { id: 4, text: "Courses", href: "#" },
  ];

  return (
    <div className="flex justify-between items-center h-24 max-w-[1280px] mx-auto px-4">
      {/* Logo */}
      <a href="/">
        <h1 className="text-2xl font-bold text-[#00df9a]">Career Connect</h1>
      </a>

      {/* Desktop Navigation */}
      <ul className="hidden md:flex">
        {navItems.map((item) => (
          <a
            key={item.id}
            href={item.href}
            className={
              activePage === item.text
                ? "p-4 text-[#00df9a] rounded-xl m-2 cursor-pointer "
                : "p-4 hover:text-[#00df9a] rounded-xl m-2 cursor-pointer duration-300"
            }
            onClick={() => setActivePage(item.text)}
          >
            {item.text}
          </a>
        ))}
      </ul>

      <button className="hidden md:flex hover:text-[#00df9a]">Login</button>

      {/* Mobile Navigation Icon */}
      <div onClick={handleNav} className="block md:hidden">
        {nav ? <AiOutlineClose size={20} /> : <AiOutlineMenu size={20} />}
      </div>

      {/* Mobile Navigation Menu */}
      <ul
        className={
          nav
            ? "fixed md:hidden left-0 top-0 w-[60%] h-full border-r border-r-gray-900 ease-in-out duration-500"
            : "ease-in-out w-[60%] duration-500 fixed top-0 bottom-0 left-[-100%]"
        }
      >
        {/* Mobile Logo */}
        <h1 className="w-full text-3xl font-bold text-[#00df9a] m-4">
          Career Connect
        </h1>

        {/* Mobile Navigation Items */}
        {navItems.map((item) => (
          <a
            key={item.id}
            className="p-4 border-b rounded-xl hover:bg-[#00df9a] duration-300 hover:text-black cursor-pointer border-gray-600"
            href={item.href}
          >
            {item.text}
          </a>
        ))}
      </ul>
    </div>
  );
}
