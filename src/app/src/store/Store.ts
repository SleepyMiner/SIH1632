import { create } from "zustand";

interface WorkModeState {
  selectedWorkMode: string;
  setSelectedWorkMode: (input: string) => void;
}

interface JobSearchState {
  searchedJob: string;
  setsearchedJob: (input: string) => void;
}

interface CompanySearchState {
  selectedCompany: string;
  setSelectedCompany: (input: string) => void;
}

interface SectorState {
  selectedSector: string;
  setSelectedSector: (input: string) => void;
}

interface AppliedJobState {
  appliedJob: string;
  setAppliedJob: (input: string) => void;
}

export const useWorkModeState = create<WorkModeState>((set) => ({
  selectedWorkMode: "All",
  setSelectedWorkMode: (input) => set({ selectedWorkMode: input }),
}));

export const useJobSearchState = create<JobSearchState>((set) => ({
  searchedJob: "",
  setsearchedJob: (input) => set({ searchedJob: input }),
}));

export const useCompanySearchState = create<CompanySearchState>((set) => ({
  selectedCompany: "",
  setSelectedCompany: (input) => set({ selectedCompany: input }),
}));

export const useSectorState = create<SectorState>((set) => ({
  selectedSector: "All",
  setSelectedSector: (input) => set({ selectedSector: input }),
}));

export const useAppliedJobState = create<AppliedJobState>((set) => ({
  appliedJob: "",
  setAppliedJob: (input) => set({ appliedJob: input }),
}));
