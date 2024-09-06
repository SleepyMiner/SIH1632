import ReactDOM from 'react-dom';
import {useState} from 'react';
import { Client } from 'appwrite';

const client = new Client();

const endpoint = import.meta.env.VITE_APPWRITE_ENDPOINT;
const project = import.meta.env.VITE_APPWRITE_PROJECT;

if (endpoint && project) {
    client
        .setEndpoint(endpoint)
        .setProject(project);
} else {
    console.error('Environment variables are not set properly.');
}



const Login = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    const Modal = ({ isOpen, onClose }: { isOpen: boolean; onClose: () => void }) => {
        if (!isOpen) return null;

        return ReactDOM.createPortal(
            <div className="sm:mx-auto sm:w-full sm:max-w-sm text-center">
            <h1>::login form under construction::</h1>
            </div>,
            document.body
        );
    };

    return (
        <>
            <button
                className="hidden md:flex hover:text-[#00df9a]"
                onClick={openModal}
            >
                Login
            </button>
            <Modal isOpen={isModalOpen} onClose={closeModal} />
        </>
    );
};

export default Login;
